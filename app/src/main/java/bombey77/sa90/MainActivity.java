package bombey77.sa90;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    MyTask myTask;
    private static final String LOG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                myTask = new MyTask();
                myTask.execute();
                //Веключаю принудительно cancel
                //В статусах андроид нет состояния cancel и в status() делаю проверку
                myTask.cancel(false);
                status();
                break;
            case R.id.btnStatus:
                status();
                break;
            default:
                break;
        }
    }

    private void status() {
        if (myTask != null) {
            if (myTask.isCancelled()) {
                myTask.cancel(false);
                Log.d(LOG, "Status is - CANCELED");
                Toast.makeText(getBaseContext(), "Status is - CANCELED", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(LOG, "Status is - " + myTask.getStatus().toString());
            Toast.makeText(getBaseContext(), "Status is - " + myTask.getStatus().toString(), Toast.LENGTH_LONG).show();
        }
    }

    private class MyTask extends AsyncTask <Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                    Log.d(LOG, "i = " + i + " Status is - " + myTask.getStatus().toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
