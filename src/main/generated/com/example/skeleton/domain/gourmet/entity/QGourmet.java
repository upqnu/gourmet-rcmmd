package com.example.skeleton.domain.gourmet.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGourmet is a Querydsl query type for Gourmet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGourmet extends EntityPathBase<Gourmet> {

    private static final long serialVersionUID = 251782973L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGourmet gourmet = new QGourmet("gourmet");

    public final com.example.skeleton.global.model.QAddress address;

    public final StringPath category = createString("category");

    public final StringPath gourmetCode = createString("gourmetCode");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath isOpen = createString("isOpen");

    public final StringPath name = createString("name");

    public final com.example.skeleton.global.model.QPoint point;

    public final NumberPath<Double> rating = createNumber("rating", Double.class);

    public QGourmet(String variable) {
        this(Gourmet.class, forVariable(variable), INITS);
    }

    public QGourmet(Path<? extends Gourmet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGourmet(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGourmet(PathMetadata metadata, PathInits inits) {
        this(Gourmet.class, metadata, inits);
    }

    public QGourmet(Class<? extends Gourmet> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.example.skeleton.global.model.QAddress(forProperty("address")) : null;
        this.point = inits.isInitialized("point") ? new com.example.skeleton.global.model.QPoint(forProperty("point")) : null;
    }

}

