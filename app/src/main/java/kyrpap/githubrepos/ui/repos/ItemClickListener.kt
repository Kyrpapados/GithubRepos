package kyrpap.githubrepos.ui.repos

import kyrpap.githubrepos.data.model.local.Repository


interface ItemClickListener {
    fun onItemClick(repository: Repository)
}