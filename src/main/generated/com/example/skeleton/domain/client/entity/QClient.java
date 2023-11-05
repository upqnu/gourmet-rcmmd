package com.example.skeleton.domain.client.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClient is a Querydsl query type for Client
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClient extends EntityPathBase<Client> {

    private static final long serialVersionUID = -1453931123L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClient client = new QClient("client");

    public final StringPath clientId = createString("clientId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final EnumPath<Permission> permission = createEnum("permission", Permission.class);

    public final com.example.skeleton.global.model.QPoint point;

    public QClient(String variable) {
        this(Client.class, forVariable(variable), INITS);
    }

    public QClient(Path<? extends Client> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClient(PathMetadata metadata, PathInits inits) {
        this(Client.class, metadata, inits);
    }

    public QClient(Class<? extends Client> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.point = inits.isInitialized("point") ? new com.example.skeleton.global.model.QPoint(forProperty("point")) : null;
    }

}

