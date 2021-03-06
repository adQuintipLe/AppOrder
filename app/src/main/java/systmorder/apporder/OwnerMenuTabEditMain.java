package systmorder.apporder;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


public class OwnerMenuTabEditMain extends Fragment {

    private Button btnUploadMain;
    private ImageView imgInMainEdit;
    private EditText edtMenuMain;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private static final int GALLERY_REQUEST = 1;

    public static Uri uriMainImg = null;
    public static Uri downloadMainUrl = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_menu_tab_edit_main, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle(OwnerMenuTab.strMenuMain);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");
        storageReference = FirebaseStorage.getInstance().getReference();

//        edtMenuMain = (EditText) v.findViewById(R.id.edtMenuMain);
//        edtMenuMain.setText(OwnerMenuTab.strMenuMain);

        imgInMainEdit = (ImageView) v.findViewById(R.id.imgInMainEdit);
        Picasso.with(getActivity()).load(OwnerMenuTab.strImgMain).into(imgInMainEdit);

        btnUploadMain = (Button) v.findViewById(R.id.btnUploadMain);
        btnUploadMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

            }
        });
    }

    private void startSavingMain() {

        StorageReference filepath = storageReference.child("imgMain").child(uriMainImg.getLastPathSegment());

        filepath.putFile(uriMainImg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                downloadMainUrl = taskSnapshot.getDownloadUrl();
                DatabaseReference newSavingMain = databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu")
                        .child(OwnerMenuTab.strMenuMain);
                newSavingMain.child("imgMain").setValue(downloadMainUrl.toString());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            uriMainImg = data.getData();
            imgInMainEdit.setImageURI(uriMainImg);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.owner_save_menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.saveMenuMain){

            startSavingMain();

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            OwnerMenuTab fragOwnerMenuTab1 = new OwnerMenuTab();
            transaction.replace(R.id.owner_activity_main, fragOwnerMenuTab1);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.deleteMenuMain){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    getActivity());

            alertDialogBuilder.setMessage("Delete "+ OwnerMenuTab.strMenuMain +" ?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu").child(OwnerMenuTab.strMenuMain).removeValue();

                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            OwnerMenuTab fragOwnerMenuTab2 = new OwnerMenuTab();
                            transaction.replace(R.id.owner_activity_main, fragOwnerMenuTab2);
                            transaction.addToBackStack(null);
                            transaction.commit();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }

        return super.onOptionsItemSelected(item);
    }
}
