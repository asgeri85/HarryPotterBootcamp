package com.example.harrypoterbootcamp

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.harrypoterbootcamp.model.CharacterModel
import com.example.harrypoterbootcamp.screen.home.ApiStatus
import com.example.harrypoterbootcamp.screen.home.CharacterAdapter

@BindingAdapter("dataList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CharacterModel>?) {
    val adapter = recyclerView.adapter as CharacterAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageApiStatus")
fun bindImageStatus(imageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING->imageView.visibility=View.GONE
        ApiStatus.DONE->imageView.visibility=View.GONE
        else->imageView.visibility=View.VISIBLE
    }
}

@BindingAdapter("progressStatus")
fun bindProgressStatus(progressBar: ProgressBar,status: ApiStatus?){
    when(status){
        ApiStatus.LOADING->progressBar.visibility=View.VISIBLE
        ApiStatus.DONE->progressBar.visibility=View.GONE
        else->progressBar.visibility=View.GONE
    }
}

@BindingAdapter("imageUrl")
fun bindImageUrl(imageView: ImageView,imageUrl:String?){
    imageUrl?.let {
        Glide.with(imageView.context).load(imageUrl).apply(
            RequestOptions().placeholder(R.drawable.ic_baseline_downloading_24).error(R.drawable.ic_baseline_error_outline_24)
        ).into(imageView)
    }
}