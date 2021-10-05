package com.ameri.converter.user.editor;

import com.ameri.converter.Converter;
import com.ameri.objects.classes.user.editor.Comment;

public class CommentConverter extends Converter<Comment> {

    public CommentConverter(Class<Comment> typeConverter) {
        super(typeConverter);
    }
}
