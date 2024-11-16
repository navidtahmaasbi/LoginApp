package com.example.loginapp.adapters;

// BoardAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.models.Category;
import com.example.loginapp.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CATEGORY = 0;
    private static final int TYPE_ADD_LIST = 1;

    private List<Category> categories;
    private boolean isAddingCategory = false;

    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < categories.size()) {
            return TYPE_CATEGORY;
        } else {

            return TYPE_ADD_LIST;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CATEGORY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
            return new CategoryViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_list, parent, false);
            return new AddListViewHolder(view);
        }
    }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == TYPE_CATEGORY) {
                // Ensure that position is within the valid range for categories
                if (position < categories.size()) {
                    Category category = categories.get(position);
                    ((CategoryViewHolder) holder).bind(category);
                }
            } else if (getItemViewType(position) == TYPE_ADD_LIST) {
                ((AddListViewHolder) holder).bind(isAddingCategory);
            }
        }

        @Override
        public int getItemCount () {
            return categories.size() + 1;
        }

        public static class CategoryViewHolder extends RecyclerView.ViewHolder {
            TextView categoryTitle;

            public CategoryViewHolder(@NonNull View itemView) {
                super(itemView);
                categoryTitle = itemView.findViewById(R.id.categoryTitle);
            }

            public void bind(Category category) {
                categoryTitle.setText(category.getTitle());
            }
        }
        class AddListViewHolder extends RecyclerView.ViewHolder {
            private final TextView addListButton;
            private final EditText addCategoryEditText;
            private final ImageButton btnSave, btnCancel;

            public AddListViewHolder(@NonNull View itemView) {
                super(itemView);
                addListButton = itemView.findViewById(R.id.addListButton);
                addCategoryEditText = itemView.findViewById(R.id.addCategoryEditText);
                btnSave = itemView.findViewById(R.id.btnSave);
                btnCancel = itemView.findViewById(R.id.btnCancel);


                addListButton.setOnClickListener(v -> {
                    isAddingCategory = true;
                    notifyItemChanged(categories.size());
                });

                btnSave.setOnClickListener(v -> {
                    String newCategory = addCategoryEditText.getText().toString().trim();
                    if (!newCategory.isEmpty()) {
                        categories.add(new Category(newCategory));
                        isAddingCategory = false;
                        notifyDataSetChanged();
                    }
                });
                btnCancel.setOnClickListener(v -> {
                    isAddingCategory = false;
                    notifyItemChanged(categories.size());
                });
            }

            public void bind(boolean isAddingCategory) {
                if (isAddingCategory) {
                    addListButton.setVisibility(View.GONE);
                    addCategoryEditText.setVisibility(View.VISIBLE);
                    btnSave.setVisibility(View.VISIBLE);
                    btnCancel.setVisibility(View.VISIBLE);
                } else {
                    addListButton.setVisibility(View.VISIBLE);
                    addCategoryEditText.setVisibility(View.GONE);
                    btnSave.setVisibility(View.GONE);
                    btnCancel.setVisibility(View.GONE);

                }
            }

            private void updateAddListState() {
                notifyItemChanged(categories.size());
            }
        }
    }


