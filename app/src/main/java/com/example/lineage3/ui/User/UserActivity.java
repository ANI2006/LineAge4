package com.example.lineage3.ui.User;

import static com.example.lineage3.databinding.ActivityMainBinding.inflate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lineage3.OnClickItemInterface;
import com.example.lineage3.ProjectModel;
import com.example.lineage3.R;
import com.example.lineage3.databinding.FragmentUserBinding;
import com.example.lineage3.databinding.UserItemLayoutBinding;

import java.util.List;

public class UserActivity extends AppCompatActivity implements OnClickItemInterface {



    private UserAdapter adapter;
    private @NonNull FragmentUserBinding binding;
    private UserViewModel userViewModel;



    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

        List<ProjectModel> projectModelList;
        private OnClickItemInterface onClickItemInterface;

        public UserAdapter(OnClickItemInterface onClickItemInterface) {
            this.onClickItemInterface = onClickItemInterface;
        }

        public UserAdapter() {
            super();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            UserItemLayoutBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.user_item_layout,parent,false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if(projectModelList!=null){
                ProjectModel projectModel=projectModelList.get(position);

                holder.binding.setUserModel(projectModel);
                holder.binding.setListener(onClickItemInterface);
            }
        }

        @Override
        public int getItemCount() {
            if(projectModelList!=null)
                return projectModelList.size();
            else return 0;
        }

        public void setUsers(List<ProjectModel> projects ){
            projectModelList=projects;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            UserItemLayoutBinding binding;

            public ViewHolder(@NonNull UserItemLayoutBinding binding) {
                super(binding.getRoot());
                this.binding=binding;
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= FragmentUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.projectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new UserAdapter(this);
        binding.projectRecyclerView.setAdapter(adapter);

        binding.addUser.setOnClickListener(view->{
            startActivity(new Intent(UserActivity.this,AddUserActivity.class));
        });
        userViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);


        userViewModel.getAllUserLive().observe(UserActivity.this, new Observer<List<ProjectModel>>() {
            @Override
            public void onChanged(List<ProjectModel> projectModelList) {

                if(projectModelList!=null){
                    adapter.setUsers(projectModelList);
                }

            }
        });

    }


    @Override
    public void onClickItem(ProjectModel projectModel, boolean isEdit) {

        if(isEdit){
            Intent intent=new Intent(UserActivity.this,AddUserActivity.class);
            intent.putExtra("model", projectModel);
            startActivity(intent);
        }else {
            userViewModel.deleteUser(projectModel);
        }



    }



}