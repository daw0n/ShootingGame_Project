package org.Framework;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundManager {
	private static SoundManager s_instance;
	private SoundPool m_SoundPool;
	private HashMap m_SoundPoolMap;
	private AudioManager m_AudioManager;

	private MediaPlayer m_MediaPlayer;

	private Context m_Activity;

	public static SoundManager getInstance() {
		if (s_instance == null) {
			s_instance = new SoundManager();
		}
		return s_instance;
	}

	public void Init(Context _context){
		m_SoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		m_SoundPoolMap = new HashMap();
		m_AudioManager = (AudioManager) _context.getSystemService(Context.AUDIO_SERVICE);
		m_MediaPlayer = null;
		m_Activity = _context;
	}

	public void addSound(int _index, int _soundID){
		int id = m_SoundPool.load(m_Activity, _soundID, 1);
		m_SoundPoolMap.put(_index, id);
	}

	public void playSound(int _index) {
		float streamVolumn = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolumn = streamVolumn / m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		
		m_SoundPool.play((Integer)m_SoundPoolMap.get(_index), streamVolumn, streamVolumn, 1, 0, 1f);

	}

	public void playMedia(int resource){
		if(m_MediaPlayer!=null)
		{
			if(m_MediaPlayer.isPlaying())
				return;
			else
				releseMedia();
		}

		float streamVolumn = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolumn = streamVolumn / m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) *0.5f;
		m_MediaPlayer = MediaPlayer.create(m_Activity, resource);
		m_MediaPlayer.setVolume(streamVolumn, streamVolumn);
		m_MediaPlayer.setLooping(true);
		m_MediaPlayer.start();

	}

	public void stopMedia()
	{
		if(m_MediaPlayer!=null)
		{
			if(m_MediaPlayer.isPlaying())
			{
				m_MediaPlayer.stop();
			}
		}
	}

	public void releseMedia()
	{
		if(m_MediaPlayer!=null)
		{
			m_MediaPlayer.release();
			m_MediaPlayer = null;
		}
	}

	public void destroy()
	{
		releseMedia();
		m_SoundPool.release();
		m_SoundPool = null;
		m_SoundPoolMap.clear();
		m_SoundPoolMap=null;
		m_AudioManager = null;
	}

}
