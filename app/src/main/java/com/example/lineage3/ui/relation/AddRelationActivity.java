package com.example.lineage3.ui.relation;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.lineage3.ProjectModel;
import com.example.lineage3.RelationUser;
import com.example.lineage3.databinding.ActivityAddUserBinding;
import com.example.lineage3.databinding.ActivityMainBinding;
import com.example.lineage3.ui.User.UserViewModel;

public class AddRelationActivity extends AppCompatActivity {
    private ActivityAddUserBinding binding;
    private String person1,person2,relationBetween;

   // private String[] genders={" Male"," Female"};
    private RelationViewModel relationViewModel;
    private RelationUser relationUser;
    private boolean isEdit=false;

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return null;
    }


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding= ActivityAddRelationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initDropDown();
        relationViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RelationViewModel.class);

        if (getIntent().hasExtra("model")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                relationUser=getIntent().getParcelableExtra("model",RelationUser.class);
            }
//            binding.edtFirstName.setText(projectModel.firstName);
//            binding.edtLastName.setText(projectModel.lastName);
//            binding.edtGender.setText(projectModel.gender);

            isEdit=true;


        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);

        binding.btnAddRelation.setOnClickListener(view -> {

            if(isEdit){
                firstName=binding.edtFirstName.getText().toString().trim();
                lastName=binding.edtLastName.getText().toString().trim();
                gender=binding.edtGender.getText().toString().trim();

                projectModel.firstName=firstName;
                projectModel.lastName=lastName;
                projectModel.gender=gender;\

                  relationViewModel.updateRelation(relationUser);
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

                finish();
            }else {
                firstName=binding.edtFirstName.getText().toString().trim();
                lastName=binding.edtLastName.getText().toString().trim();
                gender=binding.edtGender.getText().toString().trim();

                projectModel=new ProjectModel();
                projectModel.firstName=firstName;
                projectModel.lastName=lastName;
                projectModel.gender=gender;

                relationViewModel.insertRelation(relationUser);

                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();


                finish();

            }

        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void initDropDown(){
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,genders);
        binding.edtGender.setAdapter(arrayAdapter);
        binding.edtGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                gender=(String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}


