When(/^I navigate to coupon page using url "([^"]*)"$/) do |url|
  Capybara.page.visit url
end

When(/^I validate coupon\-code "([^"]*)"$/) do |coupon|
  coupon_page.coupon_form.fill_in 'Coupon Code', with: coupon
  coupon_page.coupon_form.validate_button.click_button
end

And(/^I am on coupon page$/) do
  expect(coupon_page.coupon_form).to have_text 'Apply Coupon'
end