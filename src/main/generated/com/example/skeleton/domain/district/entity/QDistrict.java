package com.example.skeleton.domain.district.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDistrict is a Querydsl query type for District
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDistrict extends EntityPathBase<District> {

    private static final long serialVersionUID = -1303414765L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDistrict district = new QDistrict("district");

    public final StringPath dosi = createString("dosi");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.skeleton.global.model.QPoint point;

    public final StringPath sgg = createString("sgg");

    public QDistrict(String variable) {
        this(District.class, forVariable(variable), INITS);
    }

    public QDistrict(Path<? extends District> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDistrict(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDistrict(PathMetadata metadata, PathInits inits) {
        this(District.class, metadata, inits);
    }

    public QDistrict(Class<? extends District> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.point = inits.isInitialized("point") ? new com.example.skeleton.global.model.QPoint(forProperty("point")) : null;
    }

}

