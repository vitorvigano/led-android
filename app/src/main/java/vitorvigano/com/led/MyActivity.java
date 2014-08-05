package vitorvigano.com.led;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyActivity extends Activity {

    Switch mPowerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mPowerSwitch = (Switch) findViewById(R.id.power);
        mPowerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean isChecked) {
                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint("http://192.168.0.22:3000")
                        .build();

                LedService service = adapter.create(LedService.class);
                // For some reason, rpi-gpio doesn't work with boolean type.
                int command = (isChecked) ? 1 : 0;

                service.power(command, new Callback<Integer>() {
                    @Override
                    public void success(Integer integer, Response response) {
                        showToast(getString(R.string.success));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        showToast(getString(R.string.error));
                    }
                });
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
