package dev.plotnikov.page.entity;

import dev.plotnikov.page.entity.payload.ElectroItemPayload;

import java.util.List;


public record ProductAllPageDTO(
        List<ElectroItemPayload> list,
        int currentPage,
        int totalPages) {
}
