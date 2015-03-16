require 'duration'
require 'time'

module MPXJ
  class Container
    attr_reader :parent_project
    def initialize(parent_project, attribute_types, attribute_values)
      @parent_project = parent_project
      @attribute_types = attribute_types
      @attribute_values = attribute_values
    end

    def method_missing(name, *args, &block)
      attribute_name = name.to_s
      attribute_type = @attribute_types[attribute_name]

      if attribute_type.nil?
        super
      else
        get_attribute_value(attribute_type, @attribute_values[attribute_name])
      end
    end

    protected

    attr_reader :attribute_values

    private

    def get_attribute_value(attribute_type, attribute_value)
      case attribute_type.to_i
      when 12, 17
        get_integer_value(attribute_value)
      when 8, 3, 5, 7
        get_float_value(attribute_value)
      when 2
        get_date_value(attribute_value)
      when 6, 16
        get_duration_value(attribute_value)
      when 4
        get_boolean_value(attribute_value)
      else
        attribute_value
      end
    end

    def get_duration_value(attribute_value)
      if attribute_value.nil?
        Duration.new(0)
      else
        Duration.new(attribute_value.to_i)
      end
    end

    def get_date_value(attribute_value)
      if attribute_value.nil?
        nil
      else
        Time.parse(attribute_value)
      end
    end

    def get_float_value(attribute_value)
      if attribute_value.nil?
        0.0
      else
        attribute_value.to_f
      end
    end

    def get_integer_value(attribute_value)
      if attribute_value.nil?
        0
      else
        attribute_value.to_i
      end
    end

    def get_boolean_value(attribute_value)
      if attribute_value.nil?
        false
      else
        attribute_value == "true"
      end
    end
  end
end