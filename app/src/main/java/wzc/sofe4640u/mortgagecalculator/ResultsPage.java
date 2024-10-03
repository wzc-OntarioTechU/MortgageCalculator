package wzc.sofe4640u.mortgagecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ResultsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        try {
            double d;
            ((EditText) findViewById(R.id.repeatPaymentAmount)).setText(intent.getStringExtra("monthlyPayment"));
            ((EditText) findViewById(R.id.termPaymentTotalAmount)).setText(intent.getStringExtra("totalPaid"));
            ((EditText) findViewById(R.id.interestTotalPaidAmount)).setText(intent.getStringExtra("interestPaid"));
            ((EditText) findViewById(R.id.principalTotalPaidAmount)).setText(intent.getStringExtra("principalPaid"));
            ((EditText) findViewById(R.id.finalBalanceAmount)).setText(intent.getStringExtra("remainingPrincipal"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}