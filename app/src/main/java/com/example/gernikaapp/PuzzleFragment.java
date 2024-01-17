package com.example.gernikaapp;

import static java.lang.Math.abs;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gernikaapp.Modelo.PuzzlePiece;
import com.example.gernikaapp.BD.Usuario;
import com.example.gernikaapp.Modelo.TouchListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PuzzleFragment extends Fragment {
    /*
    private ArrayList<PuzzlePiece> pieces;
    private String mCurrentPhotoPath;
    private String mCurrentPhotoUri;
    private TextView tiempo;
    private RelojThread r1;
    private Metodos metodos = new Metodos();
    private Usuario user;
    private boolean fragmentoVisible = false;
    private MetodosUsuario metodosUsuario = new MetodosUsuario();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Método llamado al crear la vista del fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        return inflater.inflate(R.layout.fragment_puzzle_main, container, false);
    }

    // Método llamado después de que la vista se ha creado
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener referencias a los elementos de la vista
        final RelativeLayout layout = view.findViewById(R.id.relative);
        final ImageView imageView = view.findViewById(R.id.imageView);
        Button btnAbrirFragmento = view.findViewById(R.id.btnAbrirFragmento);
        tiempo = view.findViewById(R.id.contadorTextView);
        TextView puntuacion = view.findViewById(R.id.puntuacionTextView);

        // Iniciar el contador
        iniciarContador();

        // Obtener datos del usuario y establecer la puntuación
        user = metodos.recogerDatosUsuario(requireContext());
        puntuacion.setText(user.getPuntuacion());

        // Obtener argumentos pasados al fragmento
        Bundle args = getArguments();
        if (args != null) {
            final String assetName = args.getString("assetName");
            mCurrentPhotoPath = args.getString("mCurrentPhotoPath");
            mCurrentPhotoUri = args.getString("mCurrentPhotoUri");

            // run image related code after the view was laid out
            // to have all dimensions calculated
            imageView.post(() -> {
                if (assetName != null) {
                    setPicFromAsset(assetName, imageView);
                } else if (mCurrentPhotoPath != null) {
                    setPicFromPath(mCurrentPhotoPath, imageView);
                } else if (mCurrentPhotoUri != null) {
                    imageView.setImageURI(Uri.parse(mCurrentPhotoUri));
                }
                pieces = splitImage();
                TouchListener touchListener = new TouchListener(this,PuzzleFragment);
                // shuffle pieces order
                Collections.shuffle(pieces);
                for (PuzzlePiece piece : pieces) {
                    piece.setOnTouchListener(touchListener);
                    layout.addView(piece);
                    // randomize position, on the bottom of the screen
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) piece.getLayoutParams();
                    lParams.leftMargin = new Random().nextInt(layout.getWidth() - piece.pieceWidth);
                    lParams.topMargin = layout.getHeight() - piece.pieceHeight;
                    piece.setLayoutParams(lParams);
                }
            });


            // Configurar el botón para abrir o cerrar el fragmento
            btnAbrirFragmento.setOnClickListener(v -> {
                if (fragmentoVisible) {
                    // Si el fragmento está visible, ciérralo
                    requireActivity().getSupportFragmentManager().popBackStack();
                    fragmentoVisible = false;
                } else {
                    // Si el fragmento no está visible, ábrelo
                    Puntuacion tuFragmento = new Puntuacion(); // Reemplaza con el nombre de tu fragmento

                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.relative, tuFragmento)
                            .addToBackStack(null)
                            .commit();

                    fragmentoVisible = true;
                }
            });
        }

        private void iniciarContador() {
            if (r1 == null || r1.getEstado() == 2) {
                r1 = new RelojThread(tiempo);
            } else {
                r1.setEstado(0);
            }
            if (!r1.isAlive()) {
                r1.start();
            }
        }


        private void setPicFromAsset(String assetName, ImageView imageView) {
            // Get the dimensions of the View
            int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();

            AssetManager am = getAssets();
            try {
                InputStream is = am.open("img/" + assetName);
                // Get the dimensions of the bitmap
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                // Determine how much to scale down the image
                int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

                is.reset();

                // Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;

                Bitmap bitmap = BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        private ArrayList<PuzzlePiece> splitImage() {
            int piecesNumber = 12;
            int rows = 4;
            int cols = 3;

            ImageView imageView = findViewById(R.id.imageView);
            ArrayList<PuzzlePiece> pieces = new ArrayList<>(piecesNumber);

            // Get the scaled bitmap of the source image
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            int[] dimensions = getBitmapPositionInsideImageView(imageView);
            int scaledBitmapLeft = dimensions[0];
            int scaledBitmapTop = dimensions[1];
            int scaledBitmapWidth = dimensions[2];
            int scaledBitmapHeight = dimensions[3];

            int croppedImageWidth = scaledBitmapWidth - 2 * abs(scaledBitmapLeft);
            int croppedImageHeight = scaledBitmapHeight - 2 * abs(scaledBitmapTop);

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledBitmapWidth, scaledBitmapHeight, true);
            Bitmap croppedBitmap = Bitmap.createBitmap(scaledBitmap, abs(scaledBitmapLeft), abs(scaledBitmapTop), croppedImageWidth, croppedImageHeight);

            // Calculate the with and height of the pieces
            int pieceWidth = croppedImageWidth / cols;
            int pieceHeight = croppedImageHeight / rows;

            // Create each bitmap piece and add it to the resulting array
            int yCoord = 0;
            for (int row = 0; row < rows; row++) {
                int xCoord = 0;
                for (int col = 0; col < cols; col++) {
                    // calculate offset for each piece
                    int offsetX = 0;
                    int offsetY = 0;
                    if (col > 0) {
                        offsetX = pieceWidth / 3;
                    }
                    if (row > 0) {
                        offsetY = pieceHeight / 3;
                    }

                    // apply the offset to each piece
                    Bitmap pieceBitmap = Bitmap.createBitmap(croppedBitmap, xCoord - offsetX, yCoord - offsetY, pieceWidth + offsetX, pieceHeight + offsetY);
                    PuzzlePiece piece = new PuzzlePiece(getApplicationContext());
                    piece.setImageBitmap(pieceBitmap);
                    piece.xCoord = xCoord - offsetX + imageView.getLeft();
                    piece.yCoord = yCoord - offsetY + imageView.getTop();
                    piece.pieceWidth = pieceWidth + offsetX;
                    piece.pieceHeight = pieceHeight + offsetY;

                    // this bitmap will hold our final puzzle piece image
                    Bitmap puzzlePiece = Bitmap.createBitmap(pieceWidth + offsetX, pieceHeight + offsetY, Bitmap.Config.ARGB_8888);

                    // draw path
                    int bumpSize = pieceHeight / 4;
                    Canvas canvas = new Canvas(puzzlePiece);
                    Path path = new Path();
                    path.moveTo(offsetX, offsetY);
                    if (row == 0) {
                        // top side piece
                        path.lineTo(pieceBitmap.getWidth(), offsetY);
                    } else {
                        // top bump
                        path.lineTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 3f, offsetY);
                        path.cubicTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 6f, offsetY - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 6f * 5, offsetY - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 3f * 2, offsetY);
                        path.lineTo(pieceBitmap.getWidth(), offsetY);
                    }

                    if (col == cols - 1) {
                        // right side piece
                        path.lineTo(pieceBitmap.getWidth(), pieceBitmap.getHeight());
                    } else {
                        // right bump
                        path.lineTo(pieceBitmap.getWidth(), offsetY + (pieceBitmap.getHeight() - offsetY) / 3f);
                        path.cubicTo(pieceBitmap.getWidth() - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6f, pieceBitmap.getWidth() - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6f * 5, pieceBitmap.getWidth(), offsetY + (pieceBitmap.getHeight() - offsetY) / 3f * 2);
                        path.lineTo(pieceBitmap.getWidth(), pieceBitmap.getHeight());
                    }

                    if (row == rows - 1) {
                        // bottom side piece
                        path.lineTo(offsetX, pieceBitmap.getHeight());
                    } else {
                        // bottom bump
                        path.lineTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 3f * 2, pieceBitmap.getHeight());
                        path.cubicTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 6f * 5, pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 6f, pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 3f, pieceBitmap.getHeight());
                        path.lineTo(offsetX, pieceBitmap.getHeight());
                    }

                    if (col == 0) {
                        // left side piece
                        path.close();
                    } else {
                        // left bump
                        path.lineTo(offsetX, offsetY + (pieceBitmap.getHeight() - offsetY) / 3f * 2);
                        path.cubicTo(offsetX - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6f * 5, offsetX - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6f, offsetX, offsetY + (pieceBitmap.getHeight() - offsetY) / 3f);
                        path.close();
                    }

                    // mask the piece
                    Paint paint = new Paint();
                    paint.setColor(0XFF000000);
                    paint.setStyle(Paint.Style.FILL);

                    canvas.drawPath(path, paint);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                    canvas.drawBitmap(pieceBitmap, 0, 0, paint);

                    // draw a white border
                    Paint border = new Paint();
                    border.setColor(0X80FFFFFF);
                    border.setStyle(Paint.Style.STROKE);
                    border.setStrokeWidth(8.0f);
                    canvas.drawPath(path, border);

                    // draw a black border
                    border = new Paint();
                    border.setColor(0X80000000);
                    border.setStyle(Paint.Style.STROKE);
                    border.setStrokeWidth(3.0f);
                    canvas.drawPath(path, border);

                    // set the resulting bitmap to the piece
                    piece.setImageBitmap(puzzlePiece);

                    pieces.add(piece);
                    xCoord += pieceWidth;
                }
                yCoord += pieceHeight;
            }

            return pieces;
        }

        private int[] getBitmapPositionInsideImageView(ImageView imageView) {
            int[] ret = new int[4];

            if (imageView == null || imageView.getDrawable() == null)
                return ret;

            // Get image dimensions
            // Get image matrix values and place them in an array
            float[] f = new float[9];
            imageView.getImageMatrix().getValues(f);

            // Extract the scale values using the constants (if aspect ratio maintained, scaleX == scaleY)
            final float scaleX = f[Matrix.MSCALE_X];
            final float scaleY = f[Matrix.MSCALE_Y];

            // Get the drawable (could also get the bitmap behind the drawable and getWidth/getHeight)
            final Drawable d = imageView.getDrawable();
            final int origW = d.getIntrinsicWidth();
            final int origH = d.getIntrinsicHeight();

            // Calculate the actual dimensions
            final int actW = Math.round(origW * scaleX);
            final int actH = Math.round(origH * scaleY);

            ret[2] = actW;
            ret[3] = actH;

            // Get image position
            // We assume that the image is centered into ImageView
            int imgViewW = imageView.getWidth();
            int imgViewH = imageView.getHeight();

            int top = (imgViewH - actH) / 2;
            int left = (imgViewW - actW) / 2;

            ret[0] = left;
            ret[1] = top;

            return ret;
        }

        public void checkGameOver() {
            String tiempoActual = tiempo.getText().toString();//por ejemplo 03:34, este es el tiempo que ha tardado en realizar el juego
            String puntuacionActual = user.getPuntuacion();//este deberia ser la mejor puntuacion;
            String pt = tiempoActual;
            String[] partesTiempo1 = tiempoActual.split(":");
            int[] valoresTiempoActual = recogerValores(partesTiempo1);
            String[] partesTiempo2 = puntuacionActual.split(":");
            int[] valoresPuntuacionActual = recogerValores(partesTiempo2);
            boolean nuevoRecord = comprobarNuevoRecord(valoresTiempoActual, valoresPuntuacionActual);
            if (nuevoRecord) {
                //user.setPuntuacion(pt);
                metodos.actualizarDatosUsuario(getApplicationContext(), user, pt);
                // metodosUsuario.actualizarUsuario(user, db);
            }
            if (isGameOver()) {
                finish();
            }
        }

        public boolean comprobarNuevoRecord(int[] juego, int[] jugador) {
            boolean nuevoRecord = false;
            if (!user.getPuntuacion().equals("00:00")) {
                if (juego[0] < jugador[0]) {
                    nuevoRecord = true;
                } else if (juego[0] == jugador[0]) {
                    if (juego[1] < jugador[1]) {
                        nuevoRecord = true;
                    }
                }
            } else {
                nuevoRecord=true;
            }
            return nuevoRecord;
        }

        public int[] recogerValores(String[] parte) {
            int[] MyS = new int[2];
            String parteMinuto = parte[0];
            if (parteMinuto.charAt(0) == 0) {
                MyS[0] = Character.getNumericValue(parteMinuto.charAt(1));
            } else {
                MyS[0] = Integer.parseInt(parteMinuto);
            }
            String parteSegundo = parte[1];
            if (parteSegundo.charAt(0) == 0) {
                MyS[1] = Character.getNumericValue(parteSegundo.charAt(1));
            } else {
                MyS[1] = Integer.parseInt(parteSegundo);
            }
            return MyS;
        }

        private boolean isGameOver() {
            for (PuzzlePiece piece : pieces) {
                if (piece.canMove) {
                    r1.setSeguir(false);
                    return false;
                }
            }
            return true;
        }

        private void setPicFromPath(String mCurrentPhotoPath, ImageView imageView) {
            // Get the dimensions of the View
            int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;

            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            Bitmap rotatedBitmap = bitmap;

            // rotate bitmap if needed
            try {
                ExifInterface ei = new ExifInterface(mCurrentPhotoPath);
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotatedBitmap = rotateImage(bitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotatedBitmap = rotateImage(bitmap, 180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotatedBitmap = rotateImage(bitmap, 270);
                        break;
                }
            } catch (IOException e) {
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            imageView.setImageBitmap(rotatedBitmap);
        }

        public static Bitmap rotateImage(Bitmap source, float angle) {
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                    matrix, true);
        }

    }
*/

}
