package com.example.coffieshop.Helper

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffieshop.Domains.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ManageCart {

    private val databaseRef = FirebaseDatabase.getInstance().getReference("Cart")

    fun insertItem(context: Context, newItem: ItemsModel){
        val itemId = newItem.id
        databaseRef.child("id").get().addOnSuccessListener { snapshot->
            if (snapshot.exists()){
                Toast.makeText(context, "Item already added to cart", Toast.LENGTH_SHORT).show()
            }
            else{
                databaseRef
                    .push()
                    .setValue(newItem)
                    .addOnSuccessListener { task->
                        Toast.makeText(context, "Item Added to cart", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{error->
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }
            }
        }
            .addOnFailureListener{error->
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                Log.d("TAG", error.message.toString())
            }

    }

    fun getAllItemList(): LiveData<MutableList<ItemsModel>> {
        val liveData = MutableLiveData<MutableList<ItemsModel>>()
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<ItemsModel>()
                for (itemSnapshot in snapshot.children){
                    val item = itemSnapshot.getValue(ItemsModel::class.java)
                    if (item != null){
                        itemList.add(item)
                    }
                }
                liveData.value = itemList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return liveData
    }
}