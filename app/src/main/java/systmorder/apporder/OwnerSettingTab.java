package systmorder.apporder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class OwnerSettingTab extends Fragment {

    private Button btnOwnerLogOut;
    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.owner_fragment_setting_tab,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();

        ActionBar mbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mbar.setTitle("setting");

        firebaseAuth = FirebaseAuth.getInstance();
        btnOwnerLogOut = (Button) v.findViewById(R.id.btnOwnerLogOut);

        btnOwnerLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btnOwnerLogOut){

                    firebaseAuth.signOut();
                    startActivity(new Intent(getActivity(), AllLoginActivity.class));
                    getActivity().finish();
                }
            }
        });
    }
}
