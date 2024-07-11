package com.mateuszmarcyk.walkthedog.message;

public interface MessageService {

    Message save(Message message);

    Message deleteById(Long id);
}
