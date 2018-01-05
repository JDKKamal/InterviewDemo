package com.jdkgroup.interviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jdkgroup.customviews.contact.Contact;
import com.jdkgroup.interviewdemo.view.ContactView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Callback callback;
    private List<Contact> contacts;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContactView contactView = ContactView.inflate(parent, false);
        contactView.setOnClickListener(v -> {
            if (callback != null) {
                Contact contact = ((ContactView) v).getContact();
                callback.onClick(contact);
            }
        });
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = getContact(position);
        ContactView view = holder.getContactView();
        view.bind(contact);
    }

    @Override
    public long getItemId(int position) {
        Contact contact = getContact(position);
        return contact.hashCode();
    }

    @Override
    public int getItemCount() {
        return getContacts().size();
    }

    public Contact getContact(int position) {
        return getContacts().get(position);
    }

    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = new ArrayList<>(0);
        }
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onClick(Contact contact);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ContactView contactView;

        public ViewHolder(ContactView contactView) {
            super(contactView);
            this.contactView = contactView;
        }

        public ContactView getContactView() {
            return contactView;
        }
    }
}
