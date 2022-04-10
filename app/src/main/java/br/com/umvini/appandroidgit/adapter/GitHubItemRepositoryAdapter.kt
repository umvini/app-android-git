package br.com.umvini.appandroidgit.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.com.umvini.appandroidgit.R
import br.com.umvini.appandroidgit.network.models.RepositoryItem
import java.util.concurrent.Executors

class GitHubItemRepositoryAdapter(private var mList: List<RepositoryItem>): RecyclerView.Adapter<GitHubItemRepositoryAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_repository, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemRepository = mList[position]

        holder.tvRepoName.text = itemRepository.fullName
        holder.tvRepostars.text = itemRepository.stargazersCount.toString()
        holder.tvRepoFork.text = itemRepository.forks.toString()
        holder.tvAuthorName.text = itemRepository.name
        getImage(holder.ivPhotoAuthor, itemRepository.owner.avatarURL)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvRepoName: AppCompatTextView = itemView.findViewById(R.id.tv_repo_name)
        val tvRepostars: AppCompatTextView = itemView.findViewById(R.id.tv_repo_stars)
        val tvRepoFork: AppCompatTextView = itemView.findViewById(R.id.tv_repo_fork)
        val tvAuthorName: AppCompatTextView = itemView.findViewById(R.id.tv_author_name)
        val ivPhotoAuthor: AppCompatImageView = itemView.findViewById(R.id.iv_photo_author)
    }

    private fun getImage(imageView: AppCompatImageView, url: String) {
        val executor = Executors.newSingleThreadExecutor()

        val handler = Handler(Looper.getMainLooper())

        var image: Bitmap? = null

        executor.execute {
            try {
                val `in` = java.net.URL(url).openStream()
                image = BitmapFactory.decodeStream(`in`)

                handler.post {
                    imageView.setImageBitmap(image)
                }
            }

            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addRepositories(itens: List<RepositoryItem>) {
        mList += itens
        notifyItemInserted(mList.size)
    }
}