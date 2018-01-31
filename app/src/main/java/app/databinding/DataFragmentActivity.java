package app.databinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qingxu.demoapp.R;

public class DataFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_fragment);

        getSupportFragmentManager().beginTransaction().add(R.id.frame,new DataBindingFragment()).commit();


    }
}
