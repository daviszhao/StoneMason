package io.github.daviszhao.stonemason.api.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@AllArgsConstructor
public class PageData<T extends Serializable> implements Serializable {
    private int totalItems, totalPages, currentPage;
    private List<T> items;

    public <OUTPUT extends Serializable> PageData<OUTPUT> transform(Function<T, OUTPUT> mapper) {
        return new PageData<>(totalItems, totalPages, currentPage, items.stream().map(mapper).collect(toList()));
    }

    public PageData<T> patch(Consumer<T> patcher) {
        items.forEach(patcher);
        return this;
    }
}
