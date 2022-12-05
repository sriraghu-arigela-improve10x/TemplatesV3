package com.improve10x.templatesv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends AppCompatActivity {

    public ArrayList<Template> templateList;
    public RecyclerView templateRv;
    public TemplateAdapter templateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        setData();
        templateRv();
        handleAddBtn();
    }

    public void deleteTemplate(Template template) {
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<Void> call = templateService.deleteTemplate(template.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully Delete", Toast.LENGTH_SHORT).show();
                fetchTemplate();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed On Item Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTemplate();
    }

    public void fetchTemplate() {
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<List<Template>> call = templateService.fetchTemplate();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templateAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Template", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleAddBtn() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
        });
    }

    public void templateRv() {
        templateRv = findViewById(R.id.template_rv);
        templateRv.setLayoutManager(new LinearLayoutManager(this));
        templateAdapter = new TemplateAdapter();
        templateAdapter.setData(templateList);
        templateAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Template template) {
                Toast.makeText(TemplatesActivity.this, "on item clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Template template) {
                Toast.makeText(TemplatesActivity.this, "on item delete", Toast.LENGTH_SHORT).show();
                deleteTemplate(template);
            }

            @Override
            public void onItemEdit(Template template) {
                Toast.makeText(TemplatesActivity.this, "on item edit", Toast.LENGTH_SHORT).show();
            }
        });
        templateRv.setAdapter(templateAdapter);
    }

    public void setData() {
        templateList = new ArrayList<>();

        Template kgf = new Template();
        kgf.message = "KGF\nBlock Booster Movie";
        templateList.add(kgf);

        Template rRR = new Template();
        rRR.message = "RRR\nSuper Duper Hit";
        templateList.add(rRR);
    }
}