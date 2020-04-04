//
//  RNNavigation.m
//  split_jsbundle
//
//  Created by ZhuXueliang on 2020/3/29.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNNavigation.h"
#import "AppDelegate.h"
#import "RNJSLoader.h"
#import "ReactViewController.h"

@implementation RNNavigation

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(navigateTo:(NSString *)name)
{
  NSString *bundleName = [[[RNJSLoader sharedInstance] existingModues] objectForKey:name];
  if (!bundleName) {
    return;
  }
  AppDelegate *delegate = (AppDelegate *)UIApplication.sharedApplication.delegate;
  NSURL *app = [[NSBundle mainBundle] URLForResource:bundleName withExtension:@"jsbundle"];
  ReactViewController *vc = [[ReactViewController alloc] initWithModuleName:name url:app];
  [delegate.rootNavigationController pushViewController:vc animated:YES];
}

RCT_EXPORT_METHOD(goBack)
{
  AppDelegate *delegate = (AppDelegate *)UIApplication.sharedApplication.delegate;
  [delegate.rootNavigationController popViewControllerAnimated:YES];
}

- (dispatch_queue_t)methodQueue {
  return dispatch_get_main_queue();
}

@end
