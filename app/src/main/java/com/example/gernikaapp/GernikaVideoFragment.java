package com.example.gernikaapp;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GernikaVideoFragment extends Fragment {

    Button buttonJuego;

    public GernikaVideoFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño para este fragmento
        return inflater.inflate(R.layout.fragment_gernika_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonJuego = view.findViewById(R.id.buttonJuegoE);

        // Iniciar el video y sus controles
        VideoView videoView = view.findViewById(R.id.videoViewE);
        //videoView.setVideoPath("android.resource://" + requireActivity().getPackageName() + "/" + R.raw.videocuadro);
        MediaController mediaController = new MediaController(requireContext());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVolume(1f, 1f);
            }
        });

        // Iniciar el juego
        buttonJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Aqui escribe el codigo para que vaya a tu Fragment

            }
        });
    }
}