package kr.co.hanbit.myfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NameFragment extends Fragment {
    public static NameFragment newInstance(String name) {
        NameFragment f = new NameFragment();

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        f.setArguments(bundle);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_name, container, false);

        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setView(view);
    }

    private void setView(View view) {
        String name = getArguments().getString("name");

        TextView nameText = view.findViewById(R.id.name);
        nameText.setText(name);
    }
}
