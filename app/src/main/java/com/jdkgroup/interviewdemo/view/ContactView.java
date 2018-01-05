package com.jdkgroup.interviewdemo.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdkgroup.customviews.contact.Contact;
import com.jdkgroup.interviewdemo.R;

public class ContactView extends RelativeLayout {

   private Contact contact;
   private AppCompatImageView photoView;
   private AppCompatTextView displayNameView, phoneNumberView;

   public static ContactView inflate (ViewGroup parent, boolean attachToParent) {
      if (parent == null) {
         return null;
      }
      Context context = parent.getContext();
      LayoutInflater inflater = LayoutInflater.from(context);
      return (ContactView) inflater.inflate(R.layout.itemview_contact, parent, attachToParent);
   }

   public ContactView (Context context) {
      this(context, null);
   }

   public ContactView (Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public ContactView (Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   @Override
   protected void onFinishInflate () {
      super.onFinishInflate();
      photoView = findViewById(R.id.imageView_photo);
      displayNameView = findViewById(R.id.textView_displayName);
      phoneNumberView = findViewById(R.id.textView_phoneNumber);
   }

   public void bind (@NonNull Contact contact) {
      this.contact = contact;
      updateView();
   }

   public Contact getContact () {
      return contact;
   }

   private void updateView () {
      Contact contact = getContact();
      if (contact == null) {
         String msg = "contact must be set before updating this view";
         throw new IllegalStateException(msg);
      }
      updatePhotoView(contact);
      updateDisplayNameView(contact);
      updatePhoneNumberView(contact);
   }

   private void updatePhotoView (Contact contact) {
      Uri photoUri = contact.getThumbnail();
      if (photoUri == null) {
         photoView.setImageResource(android.R.drawable.ic_input_add);
      }
      else {
         photoView.setImageURI(photoUri);
      }
   }

   private void updateDisplayNameView (Contact contact) {
      String displayName = contact.getDisplayName();
      displayNameView.setText(displayName);
   }

   private void updatePhoneNumberView (Contact contact) {
      if (!contact.getPhoneNumbers().isEmpty()) {
         String phoneNumber = contact.getPhoneNumbers().iterator().next();
         phoneNumberView.setText(phoneNumber);
      }
   }
}
