package wzc.sofe4640u.mortgagecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton calculateBtn = (FloatingActionButton) findViewById(R.id.calculateBtn);
        calculateBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                double principal = Double.parseDouble(((EditText) findViewById(R.id.principalInput)).getText().toString());
                double interest = Double.parseDouble(((EditText) findViewById(R.id.interestInput)).getText().toString());
                interest /= 12;
                int interestPeriod = Integer.parseInt(((EditText) findViewById(R.id.interestPeriodInput)).getText().toString());
                interestPeriod *= 12;
                int amortizationPeriod = Integer.parseInt(((EditText) findViewById(R.id.amortizationPeriodInput)).getText().toString());
                amortizationPeriod *= 12;

                double emi = principal * interest * (Math.pow(1 + interest, interestPeriod) / (Math.pow(1 + interest, interestPeriod) - 1));
                double totalPaid = emi * amortizationPeriod;
                double remaining = principal;
                double interestAmount = 0, principalAmount = 0;
                for (int i = 0; i < amortizationPeriod; i++) {
                    interestAmount += principal * interest;
                    principalAmount += emi - (principal * interest);
                }
                remaining -= principalAmount;

                DecimalFormat formatter = new DecimalFormat("$###,###,###,##0.00");

                Intent intent = new Intent(MainActivity.this, ResultsPage.class);
                intent.putExtra("monthlyPayment", formatter.format(emi));
                intent.putExtra("totalPaid", formatter.format(totalPaid));
                intent.putExtra("principalPaid", formatter.format(principalAmount));
                intent.putExtra("interestPaid", formatter.format(interestAmount));
                intent.putExtra("remainingPrincipal", formatter.format(remaining));
                startActivity(intent);
            }
        });

        /* MaterialSwitch lumpSumSwitch = (MaterialSwitch) findViewById(R.id.lumpSumSwitch);
        lumpSumSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                findViewById(R.id.additionalPaymentAmount).setVisibility(View.VISIBLE);
                findViewById(R.id.additionalPaymentFrequency).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.additionalPaymentAmount).setVisibility(View.INVISIBLE);
                findViewById(R.id.additionalPaymentFrequency).setVisibility(View.INVISIBLE);
            }
        }); */
    }

}