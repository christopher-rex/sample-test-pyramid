require 'bundler'
Bundler.require

require 'capybara-screenshot/cucumber'

include Capybara::DSL

Capybara.default_driver = :selenium
Capybara.save_path = File.dirname(__FILE__) + '/../cucumber_output/'
Capybara::Screenshot.prune_strategy = :keep_last_run

pageify('features/pages')
