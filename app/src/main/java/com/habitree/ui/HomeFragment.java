package com.habitree.ui;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.habitree.R;
import com.habitree.databinding.FragmentHomeBinding;
import com.habitree.models.Journal;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference;
    private List<Journal> journalList;
    private RecyclerView recyclerView;
    private JournalRecyclerAdapter journalRecyclerAdapter;

    private CollectionReference collectionReference = db.collection("Journal");
    private TextView noJournalEntry;

    private FragmentHomeBinding binding;

    public View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


}
