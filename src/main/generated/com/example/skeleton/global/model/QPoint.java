package com.example.skeleton.global.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPoint is a Querydsl query type for Point
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPoint extends BeanPath<Point> {

    private static final long serialVersionUID = 82719600L;

    public static final QPoint point = new QPoint("point");

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public QPoint(String variable) {
        super(Point.class, forVariable(variable));
    }

    public QPoint(Path<? extends Point> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPoint(PathMetadata metadata) {
        super(Point.class, metadata);
    }

}

