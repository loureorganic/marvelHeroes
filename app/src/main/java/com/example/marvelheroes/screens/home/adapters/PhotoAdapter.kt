package com.example.marvelheroes.screens.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.R
import com.example.marvelheroes.repositories.network.api.models.Result
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

class PhotoAdapter (var context: Context) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private var dataList = emptyList<Result>()


    private var glide : ImageLoader = ImageLoader()

    internal fun setDataList(userImageList: List<Result>) {
        this.dataList = userImageList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.title.text = data.name

        glide.loaderImage(
            context = context,
            imageData = data.resourceURI,
            holderImage = holder.image
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.textTitle)
    }
}