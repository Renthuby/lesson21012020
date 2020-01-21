package com.example.kutergin21012020;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProgressFragment extends Fragment {
    int[] integer = null;
    ProgressBar indicatorBar;
    TextView statusView, clicksview;
    Button btnclick;
    int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);        //сохраняет состояние при повороте экрана

    }

    @Nullable
    @Override
    /**
     * @author Renthuby
     * */
    public View onCreateView(@NonNull LayoutInflater/*класс который отвечает за раздувание*/ inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //выполняет раздувание фрагментов в MainActivity

        View view = inflater.inflate(R.layout.fragment_layout,container, false);
        integer = new int[100];
        for (int i = 0; i < integer.length; i++) {
            integer[i] = i + 1;
        }

        indicatorBar = view.findViewById(R.id.indicator);
        statusView = view.findViewById(R.id.statusview);
        Button btnFetch = view.findViewById(R.id.progressBtn);
        btnclick = view.findViewById(R.id.clickBtn);
        clicksview = view.findViewById(R.id.clicksview);


        btnFetch.setOnClickListener(new View.OnClickListener() {
            class ProgressTask extends AsyncTask<Void,Integer,Void> {


                @Override
                protected Void doInBackground(Void... voids) {
                    for (int i = 0; i < integer.length; i++) {
                        publishProgress(i);
                        SystemClock.sleep(400);
                    }

                    return null;
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getActivity(), "Задача заверешна", Toast.LENGTH_SHORT).show();
                }

                @Override
                protected void onProgressUpdate(Integer... values) {

                    indicatorBar.setProgress(values[0]+1);
                    statusView.setText("Статус: " + String.valueOf(values[0] + 1));

                    //super.onProgressUpdate(values);
                }
            }

            @Override
            public void onClick(View v) {
                new ProgressTask().execute();
            }
        });
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicksview.setText(""  + count++);

            }
        });
        return view;
        //super.onCreateView(inflater, container, savedInstanceState);
    }
}
