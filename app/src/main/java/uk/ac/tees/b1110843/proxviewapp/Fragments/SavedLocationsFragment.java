package uk.ac.tees.b1110843.proxviewapp.Fragments;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import uk.ac.tees.b1110843.proxviewapp.R;
import uk.ac.tees.b1110843.proxviewapp.databinding.FragmentSavedLocationsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;





public class SavedLocationsFragment extends Fragment  {

    private FragmentSavedLocationsBinding binding;

      View view;
      LinearLayoutManager manager;
    private FloatingActionButton  btnCapture;
    private ImageView imgCapture;
    private static final int Image_Capture_Code = 1;


    @Nullable
//    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_saved_locations, container, false);

        btnCapture = (FloatingActionButton ) view.findViewById(R.id.btnTakePicture);
        imgCapture = (ImageView)view.findViewById(R.id.capturedImage);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();

            }
        }
    }

}