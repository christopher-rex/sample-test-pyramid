require 'bundler'
Bundler.require

require 'capybara-screenshot/cucumber'

include Capybara::DSL

Capybara.default_driver = :selenium
Capybara.save_path = File.dirname(__FILE__) + '/../cucumber_output/'