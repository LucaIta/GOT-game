package com.example.guest.gotgame.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.gotgame.GotQuotesService;
import com.example.guest.gotgame.ui.GameActivity;
import com.example.guest.gotgame.R;
import com.google.firebase.auth.FirebaseAuth;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    GotQuotesService gotQuotesService = new GotQuotesService(); // to get rid of
    @Bind(R.id. playButton) Button mPlayButton;
    @Bind(R.id. gitHubLinkView) TextView mGitHubLinkView;
    @Bind(R.id. registerTextView) TextView mRegisterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getQuotes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPlayButton.setOnClickListener(this);
        mGitHubLinkView.setOnClickListener(this);
        mRegisterTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mPlayButton) {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("quotes", Parcels.wrap(gotQuotesService.mQuotes));
            startActivity(intent);
        } if (v == mGitHubLinkView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/LucaIta/GOT-game"));
            startActivity(webIntent);
        } if (v == mRegisterTextView) {
            Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        }
    }

    private void getQuotes() {
        for (int i = 0; i < 5; i++) {

            gotQuotesService.retrieveQuotes(new Callback() { // this is the callback which I pass to the service
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            Log.v(TAG, jsonData);
                            gotQuotesService.processResults(jsonData);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout(); // this is the method I create later
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}
