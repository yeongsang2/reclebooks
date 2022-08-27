package com.reclebooks.recle.dto.bookdto;


import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement(name="rss")
@Getter
public class SearchBookDtoByIsbn {


    @XmlElement(name="channel")
    private Channel channel;

    @Getter
    @XmlRootElement(name="channel")
    public static class Channel{

        @XmlElement(name="item")
        private Item item;
    }

    @XmlRootElement(name = "item")
    @Getter
    public static class Item{
        @XmlElement(name = "title")
        private String title;

        @XmlElement(name = "link")
        private String link;

        @XmlElement(name = "image")
        private String image;

        @XmlElement(name = "author")
        private String author;

        @XmlElement(name = "discount")
        private String price;

        @XmlElement(name = "publisher")
        private String publisher;

        @XmlElement(name = "pubdate")
        private String pubdate;

        @XmlElement(name = "isbn")
        private String isbn;

    }

}
