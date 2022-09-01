package com.example.study_admin.repository;

import com.example.study_admin.StudyAdminApplicationTests;
import com.example.study_admin.model.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends StudyAdminApplicationTests {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("삼성 노트북");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);

    }

    @Test
    public void read(){
        Long id = 2L;
        Optional<Item> item = itemRepository.findById(id);
        Assertions.assertTrue(item.isPresent());
    }

    @Test
    public void update(){}

    @Test
    public void delete(){}
}
