package Everest.Framework.Mvc.Binding;

/**
 * Base interface for converter class.
 * @param <TargetType> The target type of the conversion.
 * @param <SourceType> The source type of the conversion.
 *
 * @author Chendjou
 * @version 1
 * @since 15-04-2019
 */
public interface IConverter<TargetType, SourceType> {

    /**
     * The converter method.
     * @param object The source object to Converter.
     * @return The resulted object of the conversion.
     */
    TargetType convert(SourceType object);
}
