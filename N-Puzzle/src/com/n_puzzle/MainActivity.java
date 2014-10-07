package com.n_puzzle;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements Board.CounterUpdaterActivity {

	private Handler handler;
    private TextView textView;
    private TextView resultView;
    private TextUpdateRunnable textUpdateRunnable;
    private Board board;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView)findViewById(R.id.count);
		resultView = (TextView)findViewById(R.id.result_label);
		handler = new Handler();
		Button reset = (Button)findViewById(R.id.reset);
		  reset.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 board.reset();
	                 resultView.setText("");
	             }
	         });

		if (findViewById(R.id.board) != null) {

			// However, if we're being restored from a previous state,
			// then we don't need to do anything and should return or else
			// we could end up with overlapping fragments.
			if (savedInstanceState != null) {
				return;
			}

			// Create an instance of Fragment
			board = Board.newInstance(3);

			getSupportFragmentManager().beginTransaction()
					.add(R.id.board, board).commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void updateCount(int count) {
		
		if(textUpdateRunnable != null) {
            handler.removeCallbacks(textUpdateRunnable);
           }
           TextUpdateRunnable newTextUpdateRunnable = new TextUpdateRunnable(handler, textView, count); 
           textUpdateRunnable = newTextUpdateRunnable;
           handler.post(textUpdateRunnable);
	}
	
	
	private static class TextUpdateRunnable implements Runnable {
        private static final int LIMIT = 5;

        private int count = 0;
        private Handler handler;
        private TextView textView;

        public TextUpdateRunnable(Handler handler, TextView textView, int count) {
            this.handler = handler;
            this.textView = textView;
            this.count = count;
        }

        public void run() {
            if(textView != null) {
                textView.setText(""+ count);
                
                if(handler != null && count < LIMIT) {
                    handler.postDelayed(this, 3000);
                }
            }
        }
    }


	@Override
	public void gameOver() {
		resultView.setText("Congrats !!!  You did it in "+textView.getText()+"moves");
		
	}
}
