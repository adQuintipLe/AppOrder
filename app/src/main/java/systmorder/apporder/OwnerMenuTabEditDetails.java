package systmorder.apporder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.security.acl.Owner;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by mansoull on 5/6/2017.
 */

public class OwnerMenuTabEditDetails extends Fragment {

    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    public static Uri uriImg = null;
    public static Uri downloadUrl = null;
    private FirebaseAuth firebaseAuth;

    private ImageButton imgBtnEdt;
    private ListView yolo;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ProgressDialog progressDialog;

    private static final int GALLERY_REQUEST = 1;

    public static String strItemName = "";
    public static String strItemImg = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_menu_tab_edit_details,container,false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("testinh");


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");
        storageReference = FirebaseStorage.getInstance().getReference();

        progressDialog = new ProgressDialog(getActivity());

        imgBtnEdt = (ImageButton) v.findViewById(R.id.imgBtnEdt);
        imgBtnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        yolo = (ListView) v.findViewById(R.id.yolo);
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.iwilluseordelete,list);
        yolo.setAdapter(adapter);

//        databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu").child(OwnerMenuTab.strMenuMain)
//                .child(OwnerMenuTab.strMenuMain).child(OwnerMenuTabView.strMenuItem).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                strItemName = dataSnapshot.getValue().toString();
//                Log.v("Frak", strItemName);
//                list.add(strItemName);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            uriImg = data.getData();
            imgBtnEdt.setImageURI(uriImg);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.owner_save_menu_details, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.saveMenuDetails){

            startSaving();

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            OwnerMenuTabView fragOwnerMenuTabView1 = new OwnerMenuTabView();
            transaction.replace(R.id.owner_activity_main, fragOwnerMenuTabView1);
            transaction.commit();

        }
        return super.onOptionsItemSelected(item);
    }

    private void startSaving() {

        progressDialog.setMessage("saving...");
        progressDialog.show();

        StorageReference filepath = storageReference.child("img").child(uriImg.getLastPathSegment());

        filepath.putFile(uriImg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                downloadUrl = taskSnapshot.getDownloadUrl();
                DatabaseReference newSaving = databaseReference.child(AllLoginActivity.strAllRestrntID).child("tblMenu")
                        .child(OwnerMenuTab.strMenuMain).child(OwnerMenuTab.strMenuMain).child(OwnerMenuTabView.strMenuItem);

                newSaving.child("menuImage").setValue(downloadUrl.toString());
//                newSaving.child("menuImage").setValue(uriImg.getPath());

//                Picasso.with(getActivity()).load(uriImg).into(imgBtnEdt);
//                Picasso.with(ctx).load(img).into(imgBtnEdt);
//                Glide.with(getActivity()).using(new FirebaseImageLoader()).load(storageReference).into(imgBtnEdt);

                progressDialog.dismiss();
            }
        });
    }
}
