package com.mra.mvvm_tmdb.ui.MyPage.Add.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mra.mvvm_tmdb.ui.MyPage.Add.contact.Contact
import com.mra.mvvm_tmdb.ui.MyPage.Add.contact.ContactDao
import com.mra.mvvm_tmdb.ui.MyPage.Add.contact.ContactDatabase

class ContactRepository(application: Application) {

    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao: ContactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return contacts
    }

    fun insert(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.insert(contact) })
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.delete(contact)
            })
            thread.start()
        } catch (e: Exception) { }
    }

}