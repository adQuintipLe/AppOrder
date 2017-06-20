package systmorder.apporder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mansoull on 20/6/2017.
 */

public class StaffMainActivityKitchenView extends Fragment {

    private DatabaseReference databaseReference;
    private RecyclerView rvStaffKitchenView;
    private Button btnPrepare, btnServing;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.staff_activity_main_kitchen_view, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("lol");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("tblRstrn");

        rvStaffKitchenView = (RecyclerView) v.findViewById(R.id.rvStaffKitchenView);
        rvStaffKitchenView.setHasFixedSize(true);
        rvStaffKitchenView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnPrepare = (Button) v.findViewById(R.id.btnPrepare);

        btnServing = (Button) v.findViewById(R.id.btnServing);

    }
}
