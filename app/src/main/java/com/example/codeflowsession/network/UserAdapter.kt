package com.example.codeflowsession.network

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.codeflowsession.network.UserAdapter.UserViewHolder
import com.example.codeflowsession.network.UserData.User
import com.example.codeflowsession.databinding.ItemRowBinding

class UserAdapter : ListAdapter<User, UserViewHolder>(UserDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = getItem(position)
        holder.bindData(user)

    }

    class UserViewHolder(private var binding: ItemRowBinding) : ViewHolder(binding.root) {


        fun bindData(user: User) {

            binding.userEmailTv.text = user.email

            binding.userNameTv.text = "${user.firstName} ${user.lastName}"

            Glide.with(binding.root).load(user.avatar).into(binding.userIv)
        }

    }

    object UserDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {

            return oldItem == newItem

        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {

            return oldItem == newItem

        }

    }

}