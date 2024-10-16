package com.kairos.prueba.pricesapi.mapper;

import com.kairos.prueba.pricesapi.domain.Price;
import com.kairos.prueba.pricesapi.jpa.repository.entity.PriceEntity;
import com.kairos.prueba.pricesapi.rest.dto.PriceResponseDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T15:50:07+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
public class PriceMapperImpl implements PriceMapper {

    @Override
    public Price toDomain(PriceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long priceList = null;
        Long brandId = null;
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        Long productId = null;
        Integer priority = null;
        BigDecimal price = null;
        String currency = null;

        priceList = entity.getPriceList();
        brandId = entity.getBrandId();
        startDate = entity.getStartDate();
        endDate = entity.getEndDate();
        productId = entity.getProductId();
        priority = entity.getPriority();
        price = entity.getPrice();
        currency = entity.getCurrency();

        Price price1 = new Price( priceList, brandId, startDate, endDate, productId, priority, price, currency );

        return price1;
    }

    @Override
    public PriceEntity toEntity(Price price) {
        if ( price == null ) {
            return null;
        }

        PriceEntity priceEntity = new PriceEntity();

        priceEntity.setPriceList( price.getPriceList() );
        priceEntity.setBrandId( price.getBrandId() );
        priceEntity.setStartDate( price.getStartDate() );
        priceEntity.setEndDate( price.getEndDate() );
        priceEntity.setProductId( price.getProductId() );
        priceEntity.setPriority( price.getPriority() );
        priceEntity.setPrice( price.getPrice() );
        priceEntity.setCurrency( price.getCurrency() );

        return priceEntity;
    }

    @Override
    public PriceResponseDTO toDTO(Price price) {
        if ( price == null ) {
            return null;
        }

        Long productId = null;
        Long brandId = null;
        Long priceList = null;
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        BigDecimal price1 = null;
        String currency = null;

        productId = price.getProductId();
        brandId = price.getBrandId();
        priceList = price.getPriceList();
        startDate = price.getStartDate();
        endDate = price.getEndDate();
        price1 = price.getPrice();
        currency = price.getCurrency();

        PriceResponseDTO priceResponseDTO = new PriceResponseDTO( productId, brandId, priceList, startDate, endDate, price1, currency );

        return priceResponseDTO;
    }
}
