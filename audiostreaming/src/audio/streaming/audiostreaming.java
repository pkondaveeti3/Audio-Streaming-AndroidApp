package audio.streaming;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


public class audiostreaming extends Activity {
private Button streamButton;
	
	private ImageButton playButton;
	
	private TextView textStreamed;
	
	private boolean isPlaying;
	
	private StreamingMediaPlayer audioStreamer;
 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        textStreamed = (TextView) findViewById(R.id.text_kb_streamed);
		streamButton = (Button) findViewById(R.id.button_stream);
		streamButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startStreamingAudio();
        }});

		playButton = (ImageButton) findViewById(R.id.button_play);
		playButton.setEnabled(false);
		playButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (audioStreamer.getMediaPlayer().isPlaying()) {
					audioStreamer.getMediaPlayer().pause();
					playButton.setImageResource(R.drawable.button_play);
				} else {
					audioStreamer.getMediaPlayer().start();
					audioStreamer.startPlayProgressUpdater();
					playButton.setImageResource(R.drawable.button_pause);
				}
				isPlaying = !isPlaying;
        }});
       }
    private void startStreamingAudio() {
    	try { 
    		final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    		if ( audioStreamer != null) {
    			audioStreamer.interrupt();
    		}
    		audioStreamer = new StreamingMediaPlayer(textStreamed, playButton, streamButton,progressBar);
    		audioStreamer.startStreaming("http://192.168.1.143:8081/audio.mp3",1677, 214);
    		//streamButton.setEnabled(false);
    	} catch (IOException e) {
	    	Log.e(getClass().getName(), "Error starting to stream audio.", e);            		
    	}
    	    	
    }

    
}