package com.gongbo.fss.router.utils;


import com.gongbo.fss.router.enums.TypeKind;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.gongbo.fss.router.utils.Consts.BOOLEAN;
import static com.gongbo.fss.router.utils.Consts.BYTE;
import static com.gongbo.fss.router.utils.Consts.CHAR;
import static com.gongbo.fss.router.utils.Consts.DOUBEL;
import static com.gongbo.fss.router.utils.Consts.FLOAT;
import static com.gongbo.fss.router.utils.Consts.INTEGER;
import static com.gongbo.fss.router.utils.Consts.LONG;
import static com.gongbo.fss.router.utils.Consts.PARCELABLE;
import static com.gongbo.fss.router.utils.Consts.SERIALIZABLE;
import static com.gongbo.fss.router.utils.Consts.SHORT;
import static com.gongbo.fss.router.utils.Consts.STRING;

/**
 * Utils for type exchange
 *
 * @author zhilong <a href="mailto:zhilong.lzl@alibaba-inc.com">Contact me.</a>
 * @version 1.0
 * @since 2017/2/21 下午1:06
 */
public class TypeUtils {

    private Types types;
    private TypeMirror parcelableType;
    private TypeMirror serializableType;

    public TypeUtils(Types types, Elements elements) {
        this.types = types;

        parcelableType = elements.getTypeElement(PARCELABLE).asType();
        serializableType = elements.getTypeElement(SERIALIZABLE).asType();
    }

    /**
     * Diagnostics out the true java type
     *
     * @param element Raw type
     * @return Type class of java
     */
    public int typeExchange(Element element) {
        TypeMirror typeMirror = element.asType();

        // Primitive
        if (typeMirror.getKind().isPrimitive()) {
            return element.asType().getKind().ordinal();
        }

        String s = typeMirror.toString();
        if (BYTE.equals(s)) {
            return TypeKind.BYTE.ordinal();
        } else if (SHORT.equals(s)) {
            return TypeKind.SHORT.ordinal();
        } else if (INTEGER.equals(s)) {
            return TypeKind.INT.ordinal();
        } else if (LONG.equals(s)) {
            return TypeKind.LONG.ordinal();
        } else if (FLOAT.equals(s)) {
            return TypeKind.FLOAT.ordinal();
        } else if (DOUBEL.equals(s)) {
            return TypeKind.DOUBLE.ordinal();
        } else if (BOOLEAN.equals(s)) {
            return TypeKind.BOOLEAN.ordinal();
        } else if (CHAR.equals(s)) {
            return TypeKind.CHAR.ordinal();
        } else if (STRING.equals(s)) {
            return TypeKind.STRING.ordinal();
        }// Other side, maybe the PARCELABLE or SERIALIZABLE or OBJECT.
        if (types.isSubtype(typeMirror, parcelableType)) {
            // PARCELABLE
            return TypeKind.PARCELABLE.ordinal();
        } else if (types.isSubtype(typeMirror, serializableType)) {
            // SERIALIZABLE
            return TypeKind.SERIALIZABLE.ordinal();
        } else {
            return TypeKind.OBJECT.ordinal();
        }
    }

    public static TypeName getType(String type) {
        if ("byte".equals(type.toLowerCase())) {
            return TypeName.BYTE;
        } else if ("char".equals(type.toLowerCase())) {
            return TypeName.CHAR;
        } else if ("short".equals(type.toLowerCase())) {
            return TypeName.SHORT;
        } else if ("int".equals(type.toLowerCase())) {
            return TypeName.INT;
        } else if ("long".equals(type.toLowerCase())) {
            return TypeName.LONG;
        } else if ("float".equals(type.toLowerCase())) {
            return TypeName.FLOAT;
        } else if ("double".equals(type.toLowerCase())) {
            return TypeName.DOUBLE;
        } else if ("boolean".equals(type.toLowerCase())) {
            return TypeName.BOOLEAN;
        } else if ("void".equals(type.toLowerCase())) {
            return TypeName.VOID;
        }
        return ClassName.bestGuess(type);
    }
}
