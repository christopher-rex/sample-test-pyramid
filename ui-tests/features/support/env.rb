require 'bundler'
Bundler.require
require 'pageify'
require 'pageify/capybara'
require "capybara"
require 'capybara/rspec'
require 'capybara/dsl'
require 'capybara/session'
require 'capybara-screenshot/cucumber'

include Capybara::DSL


Capybara.register_driver :chrome do |app|
  Capybara::Selenium::Driver.new(app, :browser => :chrome)
end

Capybara.default_driver = :chrome
Capybara.save_path = File.dirname(__FILE__) + '/../cucumber_output/'

Capybara::Screenshot.register_driver(:chrome) do |driver, path|
  driver.browser.save_screenshot(path)
end
Capybara::Screenshot.prune_strategy = :keep_last_run

pageify('features/pages')
set_session(page)