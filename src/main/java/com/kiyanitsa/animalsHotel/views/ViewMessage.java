package com.kiyanitsa.animalsHotel.views;

public interface ViewMessage {
    public interface Id {}
    public interface IdName extends ViewMessage.Id {}
    public interface IdNameAuthorRecipient extends ViewMessage.IdName {}
    public interface FullMessage extends IdNameAuthorRecipient{}
}
