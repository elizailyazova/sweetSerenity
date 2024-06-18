package com.example.sweetsshop.ui.feedback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sweetsshop.databinding.FragmentFeedbackBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.sweetsshop.R;

public class FeedbackFragment extends Fragment {
    private FragmentFeedbackBinding binding;
    private EditText feedback_message;
    private Button feedbackBtn;
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFeedbackBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        feedback_message = binding.feedbackMessage;
        feedbackBtn = binding.feedbackBtn;

        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = feedback_message.getText().toString().trim();
                if (!message.isEmpty()) {
                    sendEmail("sweetserenity@gmail.com", "Feedback", message);
                    Toast.makeText(requireContext(), "The message is being sent: " + message, Toast.LENGTH_SHORT).show();
                    feedback_message.setText("");
                } else {
                    Toast.makeText(requireContext(), "Enter your message", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    private void sendEmail(String recipient, String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send message"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(requireContext(), "Failure", Toast.LENGTH_LONG).show();
        }
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_feedbackFragment_to_navigation_home);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

