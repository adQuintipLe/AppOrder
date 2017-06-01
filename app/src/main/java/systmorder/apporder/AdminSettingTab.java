package systmorder.apporder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URL;

import static android.app.Activity.RESULT_OK;

/**
 * Created by mansoull on 19/5/2017.
 */

public class AdminSettingTab extends Fragment {

    private Button btnLogOutAdmin, btnUpload;
    private ImageView ivUpload;
    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;

    private static final int GALLERY_INTENT = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_setting_tab,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("Setting");

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        ivUpload = (ImageView) v.findViewById(R.id.ivUpload);
        btnLogOutAdmin = (Button) v.findViewById(R.id.btnLogOutAdmin);

        btnLogOutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == btnLogOutAdmin){

                    firebaseAuth.signOut();
                    startActivity(new Intent(getActivity(), AdminLoginActivity.class));
                    getActivity().finish();
                }
            }
        });

        btnUpload = (Button) v.findViewById(R.id.btnUpload);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            Uri uri = data.getData();

            StorageReference filepath = storageReference.child("Photos").child("test");

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri dwuri = taskSnapshot.getDownloadUrl();

                    Picasso.with(getActivity()).load(dwuri).fit().centerCrop().into(ivUpload);

                    Toast.makeText(getActivity(), "testing ", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
