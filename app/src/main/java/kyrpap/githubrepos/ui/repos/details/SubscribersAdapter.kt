package kyrpap.githubrepos.ui.repos.details

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_subscribers.view.*
import kyrpap.githubrepos.R
import kyrpap.githubrepos.data.model.local.Subscribers

class SubscribersAdapter(private val mContext: Context, private val dataList : List<Subscribers>) : RecyclerView.Adapter<SubscribersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subscribers, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position],mContext)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(subscriber: Subscribers, mContext: Context) {
            itemView.nameSubscriber.text = subscriber.login

            Glide.with(mContext)
                    .applyDefaultRequestOptions(RequestOptions().override(200, 200))
                    .load(subscriber.avatarUrl)
                    .into(itemView.thumbSubscriber)
        }
    }
}