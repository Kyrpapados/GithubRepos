package kyrpap.githubrepos.ui.repos

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_repo.view.*
import kyrpap.githubrepos.R
import kyrpap.githubrepos.data.model.local.Repository

class SearchRepoAdapter(private val mContext: Context, private var dataList : MutableList<Repository>, private val clickListener: (Repository) -> Unit) : RecyclerView.Adapter<SearchRepoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position],mContext, clickListener)
    }

    fun loadMoreItems(competitionList: List<Repository>) {
        if (competitionList.isEmpty()) {
            return
        }
        val previousItemCount = dataList.size
        this.dataList.addAll(competitionList)
        notifyItemRangeInserted(previousItemCount, competitionList.size)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("StringFormatMatches")
        fun bind(repository: Repository, mContext: Context, clickListener: (Repository) -> Unit) {

            itemView.name.text = mContext.getString(R.string.repo_name,  repository.name)
            itemView.description.text = mContext.getString(R.string.repo_description, repository.description)
            itemView.forks.text = mContext.getString(R.string.repo_fork, repository.forks)

            Glide.with(mContext)
                    .applyDefaultRequestOptions(RequestOptions().override(200, 200))
                    .load(repository.owner.avatar)
                    .into(itemView.thumb)

            itemView.setOnClickListener{ clickListener(repository) }
        }
    }
}